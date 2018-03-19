package com.sandbox

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class ProcessingSpec extends Specification implements DomainUnitTest<Processing> {

    Closure doWithConfig() {{ config ->
        config.grails.databinding.convertEmptyStringsToNull = false
        config.grails.databinding.trimStrings = false
    }}

    @Unroll('create Processing with name: [#name]')
    void 'should preserve original data while data binding'() {
        when:
        Map properties = [name: name]
        Processing pd = new Processing(properties)

        then:
        pd != null
        pd.name == expected

        where:
        name     || expected
        null     || null
        ''       || ''     // empty string
        '    '   || '    ' // blank string
        'test'   || 'test'
    }
}
