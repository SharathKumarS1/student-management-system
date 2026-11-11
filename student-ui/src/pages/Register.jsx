import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { registerUser } from "../services/authService";

function Register() {

    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await registerUser({
                email,
                password
            });

            navigate("/login");

        } catch (error) {

            console.error(error);

        }
    };

    return (

        <div className="auth-container">

            <div className="auth-card">

                <h1 className="auth-title">
                    Student Management System
                </h1>

                <p className="auth-subtitle">
                    Create New Account
                </p>

                <form onSubmit={handleSubmit}>

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
                        Register
                    </button>

                </form>

                <div className="auth-footer">

                    Already have an account?

                    <Link to="/login">
                        Login
                    </Link>

                </div>

            </div>

        </div>

    );
}

export default Register;