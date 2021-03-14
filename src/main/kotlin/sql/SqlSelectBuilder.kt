package sql

import And
import Condition

class SqlSelectBuilder {

    private val columns = mutableListOf<String>()
    private var condition: Condition? = null

    private lateinit var table: String

    @Suppress("SimplifyBooleanWithConstants")
    fun build(): String {
        if (this::table.isInitialized == false) {
            throw IllegalArgumentException("undefined table")
        }

        return this.toString()
    }

    override fun toString(): String {
        val columnsToFetch = if (columns.isEmpty()) "*" else this.columns.joinToString(separator = ",")
        val conditionString = if (this.condition == null) "" else "where $condition"
        return "select $columnsToFetch from ${this.table} $conditionString "
    }

    fun select(vararg columns: String) {
        if (columns.isEmpty()) {
            throw IllegalArgumentException("columns can not be empty")
        }
        this.columns += columns
    }

    fun from(table: String) {
        this.table = table
    }

    fun where(initializer: Condition.() -> Unit) {
        condition = And().apply(initializer)
    }


}

fun query(initializer: SqlSelectBuilder.() -> Unit): SqlSelectBuilder {
    return SqlSelectBuilder().apply(initializer)
}





