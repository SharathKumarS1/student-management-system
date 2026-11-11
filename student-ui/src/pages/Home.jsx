import { useNavigate } from "react-router-dom";

function Home() {

    const navigate = useNavigate();

    const logout = () => {

        localStorage.clear();

        navigate("/login");
    };

    return (

        <div className="home-container">

            <div className="home-card">

                <h1>
                    Student Management System
                </h1>

                <h3>
                    Welcome
                </h3>

                <p>
                    Email :
                    {" "}
                    {localStorage.getItem("email")}
                </p>

                <p>
                    Role :
                    {" "}
                    {localStorage.getItem("role")}
                </p>

                <button
                    className="logout-btn"
                    onClick={logout}
                >
                    Logout
                </button>

            </div>

        </div>
    );
}

export default Home;