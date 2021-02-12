abstract class Condition {

    abstract fun addCondition(condition: Condition)

    infix fun String.eq(value: Any?) {
        addCondition(Eq(this, value))
    }

    fun and(initializer: Condition.() -> Unit) {
        addCondition(And().apply(initializer))
    }

    fun or(initializer: Condition.() -> Unit) {
        addCondition(Or().apply(initializer))
    }

}

abstract class CompositeCondition(private val sqlOperator: String) : Condition() {
    private val conditions = mutableListOf<Condition>()

    override fun addCondition(condition: Condition) {
        this.conditions += condition
    }

    override fun toString(): String {
        return if (this.conditions.size == 1) {
            this.conditions.first().toString()
        } else {
            this.conditions.joinToString(separator = " $sqlOperator ", prefix = "(", postfix = ")")
        }
    }

}


class And : CompositeCondition("and")
class Or : CompositeCondition("or")


class Eq(private val column: String, private val value: Any?) : Condition() {

    override fun addCondition(condition: Condition) {
        throw IllegalArgumentException("can not add condition for eq ")
    }

    override fun toString(): String {
        return when (value) {
            null -> {
                "$column is null"
            }
            is String -> {
                "$column = '$value'"
            }
            else -> {
                "$column = $value"
            }
        }
    }
}