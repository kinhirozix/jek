package com.kinhiro.jek.annotation

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.FILE
)
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class Actual
