const { test, expect } = require("@playwright/test");
    
    test("Get Invalid Student", async ({ request }) => {

        const response = await request.get("/api/students/99999");

        expect(response.status()).toBe(404);
    }
);
