import { useNavigate } from "react-router-dom";
import StudentForm from "../components/StudentForm";
import { createStudent } from "../services/studentService";

function AddStudentPage() {

    const navigate = useNavigate();

    const handleSave = async (student) => {

        await createStudent(student);

        navigate("/");
    };

    return (
        <div className="container mt-4">
            {/* <h2>Add Student</h2> */}

            <StudentForm onSave={handleSave} />
        </div>
    );
}

export default AddStudentPage;