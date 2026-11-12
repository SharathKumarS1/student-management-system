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

  const handleClear = () => {
    setStudent({
      name: "",
      email: "",
      department: "",
      phoneNumber: ""
    });
  };

  return (
    <div className="student-form-card">

      <form onSubmit={handleSubmit}>

        <div className="form-group-modern">

          <label>
            👤 Student Name
          </label>

          <input
            type="text"
            name="name"
            value={student.name}
            onChange={handleChange}
            placeholder="Enter student name"
            required
          />

        </div>

        <div className="form-group-modern">

          <label>
            📧 Email Address
          </label>

          <input
            type="email"
            name="email"
            value={student.email}
            onChange={handleChange}
            placeholder="Enter email address"
            required
          />

        </div>

        <div className="form-group-modern">

          <label>
            🎓 Department
          </label>

          <input
            type="text"
            name="department"
            value={student.department}
            onChange={handleChange}
            placeholder="Enter department"
          />

        </div>

        <div className="form-group-modern">

          <label>
            📱 Phone Number
          </label>

          <input
            type="text"
            name="phoneNumber"
            value={student.phoneNumber}
            onChange={handleChange}
            placeholder="Enter phone number"
          />

        </div>

        <div className="form-buttons">

          <button
            type="submit"
            className="save-btn-modern"
          >
            💾 Save Student
          </button>

          <button
            type="button"
            className="clear-btn-modern"
            onClick={handleClear}
          >
            🔄 Clear
          </button>

        </div>

      </form>

    </div>
  );
}

export default StudentForm;