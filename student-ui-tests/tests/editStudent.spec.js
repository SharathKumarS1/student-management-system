const { test } =
    require("@playwright/test");

const {
    StudentListPage
} = require("../pages/StudentListPage");

const {
    StudentFormPage
} = require("../pages/StudentFormPage");

const {
    updatedStudentData
} = require("../utils/testData");

test("Edit Student", async ({ page }) => {

        const listPage = new StudentListPage(page);

        const formPage = new StudentFormPage(page);

        await listPage.open();

        await listPage.clickEdit();

        await page.waitForURL("**/edit/*");

        await formPage.verifyFormVisible();

        await formPage.fillStudentForm(
            updatedStudentData
        );

        await formPage.clickSave();
    }
);