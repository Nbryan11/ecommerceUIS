import { useState } from "react";
import { redirect, useNavigate } from "react-router-dom";

const SignIn = () =>{
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate(); // Inicializa useNavigate


    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/api/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }),
            });

            if (response.ok) {
                const data = await response.json();
                // Guardar la información en localStorage
                localStorage.setItem('userData', JSON.stringify(data));
                alert('Inicio de sesión exitoso');
                navigate('/'); // Reemplaza '/inicio' con la ruta que desees
                window.location.reload(); // Recarga toda la página


                // Redirigir o realizar alguna acción adicional
            } else {
                alert('Credenciales incorrectas');
            }
        } catch (error) {
            console.error('Error al iniciar sesión:', error);
        }
    };
    return(
        <div className="d-flex justify-content-center align-items-center vh-100">
        <div className="card p-4 shadow-sm" style={{ width: '400px' }}>
            <h2 className="text-center mb-4">Sign In</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">Correo electrónico</label>
                    <input
                        type="email"
                        className="form-control"
                        id="email"
                        placeholder="Ingresa tu correo"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">Contraseña</label>
                    <input
                        type="password"
                        className="form-control"
                        id="password"
                        placeholder="Ingresa tu contraseña"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <button type="submit" className="btn btn-primary w-100">Ingresar</button>
            </form>
        </div>
    </div>
    )
}

export default SignIn;