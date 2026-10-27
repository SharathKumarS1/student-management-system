class ApiClient {

    constructor(request) {
        this.request = request;
    }

    async createStudent(student) {

        return await this.request.post(
            "/api/students",
            {
                data: student
            }
        );
    }

    async getStudent(id) {

        return await this.request.get(
            `/api/students/${id}`
        );
    }

    async getAllStudents() {

        return await this.request.get(
            "/api/students"
        );
    }

    async updateStudent(
        id,
        student
    ) {

        return await this.request.put(
            `/api/students/${id}`,
            {
                data: student
            }
        );
    }

    async deleteStudent(id) {

        return await this.request.delete(
            `/api/students/${id}`
        );
    }
}

module.exports = {
    ApiClient
};