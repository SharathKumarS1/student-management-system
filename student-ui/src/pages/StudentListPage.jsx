import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import StudentList from "../components/StudentList";

import {
  getStudents,
  deleteStudent
} from "../services/studentService";

function StudentListPage() {

  const [students, setStudents] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {

    const token =
      localStorage.getItem("token");

    if (!token) {
      navigate("/login");
      return;
    }

    loadStudents();

  }, [navigate]);

  const loadStudents = async () => {

    try {

      const response =
        await getStudents();

      setStudents(
        response.data
      );

    } catch (error) {

      console.error(
        "Error loading students:",
        error
      );
    }
  };

  const handleDelete = async (id) => {

    const confirmDelete =
      window.confirm(
        "Are you sure you want to delete this student?"
      );

    if (!confirmDelete) {
      return;
    }

    try {

      await deleteStudent(id);

      loadStudents();

    } catch (error) {

      console.error(
        "Error deleting student:",
        error
      );
    }
  };

  const handleEdit = (student) => {

    navigate(
      `/edit/${student.id}`
    );
  };

  const handleLogout = () => {

    localStorage.clear();

    navigate("/login");
  };

  return (

    <div className="student-page">

      <div className="card custom-card">

        <div
          className="
          custom-header
          d-flex
          justify-content-between
          align-items-center
          "
        >

          <div>

            <h1>
            🎓 Student Management System
            </h1>

            <div className="welcome-user">

              👋 Welcome,
              {" "}
              {localStorage.getItem("email")}

            </div>

          </div>

          <div className="header-actions">

            <button
              className="
              btn
              add-btn
              "
              onClick={() =>
                navigate("/add")
              }
            >
              Add Student
            </button>

            <button
              className="
              btn
              logout-btn
              "
              onClick={handleLogout}
            >
              Logout
            </button>

          </div>

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