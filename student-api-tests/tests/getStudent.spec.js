const { test, expect } =
    require("@playwright/test");

const { ApiClient } =
    require("../utils/apiClient");

const { studentData } =
    require("../utils/testData");

test(
    "Get Student",
    async ({ request }) => {

        const api =
            new ApiClient(request);

        const createResponse =
            await api.createStudent(
                studentData
            );

        const createdStudent =
            await createResponse.json();

        const getResponse =
            await api.getStudent(
                createdStudent.id
            );

        expect(
            getResponse.status()
        ).toBe(200);

        const body =
            await getResponse.json();

        expect(body.id)
            .toBe(createdStudent.id);

        expect(body.name)
            .toBe(studentData.name);
    }
);