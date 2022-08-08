package creational

data class User(
        val name: String,
        val role: Role,
        val permissions: Set<String>
) {
        fun hasPermission(permission: String) = permission in permissions
}

enum class Role {
        ADMIN, SUPER_ADMIN, REGULAR_USER;
}

val allUsers = arrayListOf<User>()


fun createUser(_name: String, role: Role) {
        allUsers.forEach {
                if (it.role == role) {
                        allUsers += it.copy(name = _name) // this is portotype
                        return
                }
        }
        allUsers += User(
                name = _name,
                role,
                permissions = setOf()
        )
}