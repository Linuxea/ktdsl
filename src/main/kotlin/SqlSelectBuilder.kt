class SqlSelectBuilder {

    private val columns = mutableListOf<String>()

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
        return "select $columnsToFetch from ${this.table}"
    }

    fun select(vararg columns: String) {
        if (columns.isEmpty()) {
            throw IllegalArgumentException("columns can not be empty")
        }
        this.columns.addAll(columns)
    }

    fun from(table: String) {
        this.table = table
    }


}

fun query(initializer: SqlSelectBuilder.() -> Unit): SqlSelectBuilder {
    return SqlSelectBuilder().apply(initializer)
}


fun main() {
    val build = query {
        select("name", "age", "sex", "address")
        from("user")
    }.build()
    println(build)
}


