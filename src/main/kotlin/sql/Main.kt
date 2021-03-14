import sql.query

fun main() {
    val build = query {
        select("name", "age", "sex", "address")
        from("user")
        where {
            "name" eq "shit"
            "sex" eq "boy"
            or {
                "age" eq 12
                "address" eq "北京"
            }
            and {
                "error" eq "error"
            }
        }
    }.build()
    println(build)
}