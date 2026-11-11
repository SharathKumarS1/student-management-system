import { useState } from "react";
import { Link, useNavigate  } from "react-router-dom";
import { loginUser } from "../services/authService";

function Login() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const handleLogin = async (e) => {

        e.preventDefault();

        try {

            const response =
                await loginUser({
                    email,
                    password
                });

            localStorage.setItem(
                "token",
                response.token
            );

            localStorage.setItem(
                "email",
                response.email
            );

            localStorage.setItem(
                "role",
                response.role
            );

            // window.location.href = "/";
            navigate("/");

        } catch (error) {

            console.error(error);

            alert("Invalid Credentials");
        }
    };

    return (

        <div className="auth-container">

            <div className="auth-card">

                <h1 className="auth-title">
                    Student Management System
                </h1>

                <p className="auth-subtitle">
                    Login to Continue
                </p>

                <form onSubmit={handleLogin}>

                    <input
                        type="email"
                        placeholder="Enter Email"
                        value={email}
                        onChange={(e) =>
                            setEmail(e.target.value)
                        }
                        required
                    />

                    <input
                        type="password"
                        placeholder="Enter Password"
                        value={password}
                        onChange={(e) =>
                            setPassword(e.target.value)
                        }
                        required
                    />

                    <button
                        type="submit"
                        className="auth-btn"
                    >
                        Login
                    </button>

                </form>

                <div className="auth-footer">

                    Don't have an account?

                    <Link to="/register">
                        Register
                    </Link>

                </div>

            </div>

        </div>

    );
}

export default Login;