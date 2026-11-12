import { useNavigate } from "react-router-dom";

import StudentForm from "../components/StudentForm";

import {
  createStudent
} from "../services/studentService";

function AddStudentPage() {

  const navigate = useNavigate();

  const handleSave = async (student) => {
    await createStudent(student);
    navigate("/");
  };

  return (
    <div className="student-page">

      <div className="page-header-modern">

        <div>

          <h1>➕ Add Student</h1>

          <p>
            Create a new student record
          </p>

        </div>

        <button
          className="back-btn"
          onClick={() => navigate("/")}
        >
          ← Back
        </button>

      </div>

      <StudentForm onSave={handleSave} />

    </div>
  );
}

export default AddStudentPage;