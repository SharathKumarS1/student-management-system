const { test, expect } =
    require("@playwright/test");

const { ApiClient } =
    require("../utils/apiClient");

const {
    studentData,
    updatedStudentData
} = require("../utils/testData");

test(
    "Update Student",
    async ({ request }) => {

        const api =
            new ApiClient(request);

        const createResponse =
            await api.createStudent(
                studentData
            );

        const createdStudent =
            await createResponse.json();

        const updateResponse =
            await api.updateStudent(
                createdStudent.id,
                updatedStudentData
            );

        expect(
            updateResponse.status()
        ).toBe(200);

        const updatedStudent =
            await updateResponse.json();

        expect(
            updatedStudent.name
        ).toBe(
            updatedStudentData.name
        );

        expect(
            updatedStudent.department
        ).toBe("ECE");
    }
);