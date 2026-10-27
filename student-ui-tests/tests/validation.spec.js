const { test, expect }
    = require("@playwright/test");

test(
    "Add Page Opens",
    async ({ page }) => {

        await page.goto(
            "http://localhost:3000/add"
        );

        await expect(
            page.locator(
                'input[name="name"]'
            )
        ).toBeVisible();

        await expect(
            page.locator(
                'input[name="email"]'
            )
        ).toBeVisible();
    }
);