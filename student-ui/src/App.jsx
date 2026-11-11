import "./App.css";

import {
  BrowserRouter,
  Routes,
  Route,
  Navigate
} from "react-router-dom";

import StudentListPage from "./pages/StudentListPage";
import AddStudentPage from "./pages/AddStudentPage";
import EditStudentPage from "./pages/EditStudentPage";
import Register from "./pages/Register";
import Login from "./pages/Login";

function App() {

  return (

    <BrowserRouter>

      <Routes>

        <Route
          path="/register"
          element={<Register />}
        />

        <Route
          path="/login"
          element={<Login />}
        />

        <Route
          path="/"
          element={
            localStorage.getItem("token")
              ? <StudentListPage />
              : <Navigate to="/login" />
          }
        />

        <Route
          path="/add"
          element={
            localStorage.getItem("token")
              ? <AddStudentPage />
              : <Navigate to="/login" />
          }
        />

        <Route
          path="/edit/:id"
          element={
            localStorage.getItem("token")
              ? <EditStudentPage />
              : <Navigate to="/login" />
          }
        />

      </Routes>

    </BrowserRouter>

  );
}

export default App;