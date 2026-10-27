import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import StudentList from "../components/StudentList";
import { getStudents, deleteStudent } from "../services/studentService";

function StudentListPage() {
    const [students, setStudents] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        loadStudents();
    }, []);

    const loadStudents = async () => {
        try {
            const response = await getStudents();
            setStudents(response.data);
        } catch (error) {
            console.error("Error loading students:", error);
        }
    };

    const handleDelete = async (id) => {
        const confirmDelete = window.confirm(
            "Are you sure you want to delete this student?"
        );

        if (!confirmDelete) {
            return;
        }

        try {
            await deleteStudent(id);
            loadStudents();
        } catch (error) {
            console.error("Error deleting student:", error);
        }
    };

    const handleEdit = (student) => {
        navigate(`/edit/${student.id}`);
    };

    return (
        <div className="container mt-5">
            <div className="card shadow">
                <div className="card-header bg-primary text-white d-flex justify-content-between align-items-center">
                    <h2 className="mb-0">Student Management System</h2>

                    <button
                        className="btn btn-light"
                        onClick={() => navigate("/add")}
                    >
                        Add Student
                    </button>
                </div>

                <div className="card-body">
                    <StudentList
                        students={students}
                        onEdit={handleEdit}
                        onDelete={handleDelete}
                    />
                </div>
            </div>
        </div>
    );
}

export default StudentListPage;