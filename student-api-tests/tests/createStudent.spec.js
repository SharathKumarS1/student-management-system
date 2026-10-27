const { test, expect } =
    require("@playwright/test");

const { ApiClient } =
    require("../utils/apiClient");

const { studentData } =
    require("../utils/testData");

test(
    "Create Student",
    async ({ request }) => {

        const api =
            new ApiClient(request);

        const response =
            await api.createStudent(
                studentData
            );

        expect(
            response.status()
        ).toBe(200);

        const body =
            await response.json();

        expect(body.id)
            .toBeDefined();

        expect(body.name)
            .toBe(studentData.name);

        expect(body.email)
            .toBe(studentData.email);
    }
);