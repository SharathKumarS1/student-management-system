const { expect } = require("@playwright/test");

class StudentFormPage {

    constructor(page) {

        this.page = page;

        this.name =
            page.locator(
                'input[name="name"]'
            );

        this.email =
            page.locator(
                'input[name="email"]'
            );

        this.department =
            page.locator(
                'input[name="department"]'
            );

        this.phoneNumber =
            page.locator(
                'input[name="phoneNumber"]'
            );

        this.saveButton =
            page.getByRole(
                "button",
                {
                    name: "Save"
                }
            );
    }

    async enterName(name) {

        await this.name.fill(name);
    }

    async enterEmail(email) {

        await this.email.fill(email);
    }

    async enterDepartment(department) {

        await this.department.fill(
            department
        );
    }

    async enterPhoneNumber(phone) {

        await this.phoneNumber.fill(
            phone
        );
    }

    async fillStudentForm(student) {

        await this.enterName(
            student.name
        );

        await this.enterEmail(
            student.email
        );

        await this.enterDepartment(
            student.department
        );

        await this.enterPhoneNumber(
            student.phoneNumber
        );
    }

    async clickSave() {

        await this.saveButton.click();
    }

    async createStudent(student) {

        await this.fillStudentForm(
            student
        );

        await this.clickSave();
    }

    async verifyFormVisible() {

        await expect(
            this.name
        ).toBeVisible();
    }
}

module.exports = {
    StudentFormPage
};