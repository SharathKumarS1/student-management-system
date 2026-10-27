const { test, expect } =
    require("@playwright/test");

const { ApiClient } =
    require("../utils/apiClient");

const { studentData } =
    require("../utils/testData");

test(
    "Delete Student",
    async ({ request }) => {

        const api =
            new ApiClient(request);

        const createResponse =
            await api.createStudent(
                studentData
            );

        const createdStudent =
            await createResponse.json();

        const deleteResponse =
            await api.deleteStudent(
                createdStudent.id
            );

        expect(
            deleteResponse.status()
        ).toBe(200);

        const getResponse =
            await api.getStudent(
                createdStudent.id
            );

        expect(
            getResponse.status()
        ).toBe(404);
    }
);