const { test } =
    require("@playwright/test");

const {
    StudentListPage
} = require("../pages/StudentListPage");

const {
    StudentFormPage
} = require("../pages/StudentFormPage");

const {
    studentData
} = require("../utils/testData");

test(
    "Add Student",
    async ({ page }) => {

        const listPage =
            new StudentListPage(page);

        const formPage =
            new StudentFormPage(page);

        await listPage.open();

        await listPage.clickAddStudent();

        await page.waitForURL("**/add");

        await formPage.verifyFormVisible();

        await formPage.fillStudentForm(studentData);

        await formPage.clickSave();
    }
);