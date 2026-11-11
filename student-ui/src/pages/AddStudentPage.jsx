import { useNavigate } from "react-router-dom";

import StudentForm
from "../components/StudentForm";

import {
  createStudent
}
from "../services/studentService";

function AddStudentPage() {

  const navigate = useNavigate();

  const handleSave = async (
    student
  ) => {

    await createStudent(student);

    navigate("/");
  };

  return (

    <div className="student-page">

      <div className="custom-card">

        <div className="custom-header">

          <h1>
            ➕ Add Student
          </h1>

          <p>
            Create a new student record
          </p>

        </div>

        <div className="card-body">

          <StudentForm
            onSave={handleSave}
          />

        </div>

      </div>

    </div>

  );
}

export default AddStudentPage;