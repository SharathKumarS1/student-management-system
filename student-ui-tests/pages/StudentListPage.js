const { expect } = require("@playwright/test");

class StudentListPage {

    constructor(page) {
        this.page = page;

        this.addStudentButton =
            page.getByRole("button", {
                name: "Add Student"
            });

        this.studentTable =
            page.locator("table");
    }

    async open() {
        await this.page.goto(
            "http://localhost:3000"
        );
    }

    async clickAddStudent() {
        await this.addStudentButton.click();
    }

    async clickEdit(studentName) {

        const row = this.page
            .locator('tbody tr')
            .filter({ hasText: studentName })
            .first();

        await row.locator('.btn-warning').click();
    }

    async clickDelete(studentName) {

        const row = this.page
            .locator('tbody tr')
            .filter({ hasText: studentName })
            .first();

        await row.locator('.btn-danger').click();
    }

    async verifyStudentExists(studentName) {

        await expect(
            this.page.getByText(studentName)
        ).toBeVisible();
    }

    async verifyStudentNotExists(studentName) {

        await expect(
            this.page.getByText(studentName)
        ).not.toBeVisible();
    }
}

module.exports = {
    StudentListPage
};