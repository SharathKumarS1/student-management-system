import React, { useEffect, useState } from "react";

function StudentForm({
  onSave,
  selectedStudent
}) {

  const [student, setStudent] =
    useState({
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
      [e.target.name]:
        e.target.value
    });
  };

  const handleSubmit = (e) => {

    e.preventDefault();

    onSave(student);
  };

  return (

    <div className="student-form-card">

      <form onSubmit={handleSubmit}>

        <div className="mb-3">

          <label>
            👤 Student Name
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

          <label>
            📧 Email
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

          <label>
            🎓 Department
          </label>

          <input
            type="text"
            className="form-control"
            name="department"
            value={student.department}
            onChange={handleChange}
          />

        </div>

        <div className="mb-4">

          <label>
            📱 Phone Number
          </label>

          <input
            type="text"
            className="form-control"
            name="phoneNumber"
            value={student.phoneNumber}
            onChange={handleChange}
          />

        </div>

        <div className="d-flex gap-2">

          <button
            type="submit"
            className="btn btn-success"
          >
            💾 Save
          </button>

          <button
            type="reset"
            className="btn btn-secondary"
          >
            🔄 Clear
          </button>

        </div>

      </form>

    </div>
  );
}

export default StudentForm;