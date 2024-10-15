package id.android.skeleton.ui.components.details

data class Detail(
    val id: Int,
    val label: String,
    val value1: String?,
    val value2: String? = null,
    val value3: String? = null,
    val value4: String? = null,
    val altValue1: String? = null,
    val altValue2: String? = null,
    val altValue3: String? = null,
    val altValue4: String? = null,
    val optionalFieldCondition: Boolean = arrayOf(
        !value2.isNullOrEmpty(),
        !value3.isNullOrEmpty(),
        !value4.isNullOrEmpty(),
        !altValue1.isNullOrEmpty(),
        !altValue2.isNullOrEmpty(),
        !altValue3.isNullOrEmpty(),
        !altValue4.isNullOrEmpty(),
    ).any { true },
    val optionalAltFiedlCondition: Boolean = arrayOf(
        !altValue1.isNullOrEmpty(),
        !altValue2.isNullOrEmpty(),
        !altValue3.isNullOrEmpty(),
        !altValue4.isNullOrEmpty(),
    ).any { true },
)
