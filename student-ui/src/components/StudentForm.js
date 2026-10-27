import React, { useEffect, useState } from "react";

function StudentForm({ onSave, selectedStudent }) {
    const [student, setStudent] = useState({
        name: "",
        email: "",
        department: "",
        phoneNumber: ""
    });

    useEffect(() => {
        if (selectedStudent) {
            setStudent(selectedStudent);
        }
    }, [selectedStudent]);

    const handleChange = (e) => {
        setStudent({
            ...student,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSave(student);
    };

    return (
        <div className="container mt-4">
            <div className="card shadow">
                <div className="card-header bg-primary text-white">
                    <h3 className="mb-0">
                        {selectedStudent
                            ? "Edit Student"
                            : "Add Student"}
                    </h3>
                </div>

                <div className="card-body">
                    <form onSubmit={handleSubmit}>

                        <div className="mb-3">
                            <label className="form-label">
                                Name
                            </label>

                            <input
                                type="text"
                                className="form-control"
                                name="name"
                                value={student.name}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="mb-3">
                            <label className="form-label">
                                Email
                            </label>

                            <input
                                type="email"
                                className="form-control"
                                name="email"
                                value={student.email}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="mb-3">
                            <label className="form-label">
                                Department
                            </label>

                            <input
                                type="text"
                                className="form-control"
                                name="department"
                                value={student.department}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="mb-3">
                            <label className="form-label">
                                Phone Number
                            </label>

                            <input
                                type="text"
                                className="form-control"
                                name="phoneNumber"
                                value={student.phoneNumber}
                                onChange={handleChange}
                            />
                        </div>

                        <button
                            type="submit"
                            className="btn btn-success me-2"
                        >
                            Save
                        </button>

                        <button
                            type="reset"
                            className="btn btn-secondary"
                        >
                            Clear
                        </button>

                    </form>
                </div>
            </div>
        </div>
    );
}

export default StudentForm;