import React, {
  useEffect,
  useState
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

  const [student,
        setStudent]
        = useState(null);

  useEffect(() => {

    loadStudent();

  }, []);

  const loadStudent = async () => {

    const response =
      await getStudent(id);

    setStudent(response.data);
  };

  const handleUpdate =
    async (updatedStudent) => {

      await updateStudent(
        id,
        updatedStudent
      );

      navigate("/");
  };

  return (
    <div className="container mt-4">

      {student && (

        <StudentForm
          selectedStudent={student}
          onSave={handleUpdate}
        />

      )}

    </div>
  );
}

export default EditStudentPage;