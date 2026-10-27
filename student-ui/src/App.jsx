import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";

import StudentListPage from "./pages/StudentListPage";
import AddStudentPage from "./pages/AddStudentPage";
import EditStudentPage from "./pages/EditStudentPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={<StudentListPage />}
        />

        <Route
          path="/add"
          element={<AddStudentPage />}
        />

        <Route
          path="/edit/:id"
          element={<EditStudentPage />}
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;