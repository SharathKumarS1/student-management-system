import React, {
  useEffect,
  useState,
  useCallback
} from "react";

import {
  useParams,
  useNavigate
} from "react-router-dom";

import StudentForm from "../components/StudentForm";

import {
  getStudent,
  updateStudent
} from "../services/studentService";

function EditStudentPage() {

  const { id } = useParams();

  const navigate = useNavigate();

  const [student, setStudent] =
    useState(null);

  const loadStudent = useCallback(
    async () => {

      const response =
        await getStudent(id);

      setStudent(response.data);

    },
    [id]
  );

  useEffect(() => {

    loadStudent();

  }, [loadStudent]);

  const handleUpdate =
    async (updatedStudent) => {

      await updateStudent(
        id,
        updatedStudent
      );

      navigate("/");
    };

  return (

  <div className="student-page">

    <div className="custom-card">

      <div className="custom-header">

        <h1>
          ✏️ Edit Student
        </h1>

        <p>
          Update student details
        </p>

      </div>

      <div className="card-body">

        {student && (

          <StudentForm
            selectedStudent={student}
            onSave={handleUpdate}
          />

        )}

      </div>

    </div>

  </div>

);
}

export default EditStudentPage;