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
    const token = localStorage.getItem("token");

    if (!token) {
      navigate("/login");
      return;
    }

    loadStudents();
  }, [navigate]);

  const loadStudents = async () => {
    try {
      const response = await getStudents();
      setStudents(response.data);
    } catch (error) {
      console.error(
        "Error loading students:",
        error
      );
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
      console.error(
        "Error deleting student:",
        error
      );
    }
  };

  const handleEdit = (student) => {
    navigate(`/edit/${student.id}`);
  };

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <div className="dashboard-container">

      {/* TOP HEADER */}

      <div className="dashboard-header">
        <div>
          <h1>🎓 Student Management System</h1>
          <p>
            Manage students efficiently through a modern dashboard
          </p>
        </div>

        <button
          className="btn btn-danger"
          onClick={handleLogout}
        >
          Logout
        </button>
      </div>

      {/* STATS */}

      <div className="stats-container">

        <div className="stat-card">
          <h3>{students.length}</h3>
          <p>Total Students</p>
        </div>

        <div className="stat-card">
          <h3>
            {localStorage.getItem("email")}
          </h3>
          <p>Logged In User</p>
        </div>

      </div>

      {/* STUDENT TABLE SECTION */}

      <div className="student-data-card">

        <div className="table-header">

          <h2>Students List</h2>

          <button
            className="btn add-student-btn"
            onClick={() => navigate("/add")}
          >
            + Add Student
          </button>

        </div>

        <StudentList
          students={students}
          onEdit={handleEdit}
          onDelete={handleDelete}
        />

      </div>

    </div>
  );
}

export default StudentListPage;
