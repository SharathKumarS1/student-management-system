import React, { useState } from "react";

function StudentList({ students, onEdit, onDelete }) {
  const [searchTerm, setSearchTerm] = useState("");

  const filteredStudents = students.filter((student) =>
    student.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    student.email.toLowerCase().includes(searchTerm.toLowerCase()) ||
    student.department.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div>
      {/* Search Box */}

      <div className="search-container">
        <input
          type="text"
          placeholder="🔍 Search Student..."
          className="search-input"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      {/* Empty State */}

      {filteredStudents.length === 0 ? (
        <div className="empty-state">
          <div className="empty-icon">
            🎓
          </div>

          <h3>No Students Found</h3>

          <p>
            Start building your student database by
            adding your first student.
          </p>

          <button
            className="add-first-student-btn"
            onClick={() => (window.location.href = "/add")}
          >
            + Add First Student
          </button>
        </div>
      ) : (
        <div className="table-responsive">
          <table className="modern-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Student Name</th>
                <th>Email</th>
                <th>Department</th>
                <th>Phone</th>
                <th>Actions</th>
              </tr>
            </thead>

            <tbody>
              {filteredStudents.map((student) => (
                <tr key={student.id}>
                  <td>{student.id}</td>

                  <td>
                    <div className="student-name">
                      👨‍🎓 {student.name}
                    </div>
                  </td>

                  <td>{student.email}</td>

                  <td>
                    <span className="department-badge">
                      {student.department}
                    </span>
                  </td>

                  <td>{student.phoneNumber}</td>

                  <td>
                    <button
                      className="edit-btn"
                      onClick={() => onEdit(student)}
                    >
                      ✏ Edit
                    </button>

                    <button
                      className="delete-btn"
                      onClick={() => onDelete(student.id)}
                    >
                      🗑 Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>

          </table>
        </div>
      )}
    </div>
  );
}

export default StudentList;