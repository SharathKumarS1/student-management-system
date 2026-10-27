import React from "react";

function StudentList({ students, onEdit, onDelete }) {
    if (!students || students.length === 0) {
        return (
            <div className="alert alert-info">
                No students found.
            </div>
        );
    }

    return (
        <table className="table table-striped table-hover">
            <thead className="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Department</th>
                    <th>Phone</th>
                    <th style={{ width: "180px" }}>Actions</th>
                </tr>
            </thead>

            <tbody>
                {students.map((student) => (
                    <tr key={student.id}>
                        <td>{student.id}</td>
                        <td>{student.name}</td>
                        <td>{student.email}</td>
                        <td>{student.department}</td>
                        <td>{student.phoneNumber}</td>

                        <td>
                            <button
                                className="btn btn-warning btn-sm me-2"
                                onClick={() => onEdit(student)}
                            >
                                Edit
                            </button>

                            <button
                                className="btn btn-danger btn-sm"
                                onClick={() => onDelete(student.id)}
                            >
                                Delete
                            </button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
}

export default StudentList;