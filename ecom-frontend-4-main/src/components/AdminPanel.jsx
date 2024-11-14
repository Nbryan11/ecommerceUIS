import React, { useEffect, useState } from 'react';
import { CiUser } from "react-icons/ci";
import { Link, Outlet, useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

const AdminPanel = () => {

    const [userData, setUserData] = useState(null);
    const navigate = useNavigate(); // Hook para navegar a otra ruta

    useEffect(() =>{
        const storedUserData = localStorage.getItem("userData");
    
        if (storedUserData) {
          // Parsear los datos si existen
          setUserData(JSON.parse(storedUserData));
        }
    },[])

    const handleLogout = () => {
        localStorage.removeItem("userData"); // Elimina los datos del usuario del localStorage
        navigate("/"); // Redirige al usuario a la página de inicio
        window.location.reload(); // Recarga toda la página

    };
    return (
        <div className='d-flex min-vh-100' style={{ marginTop: '60px'}}  >
            {/* Sidebar panel */}
            <aside className='bg-light border-end' style={{ width: '20%', minHeight: '100vh' }}>
                <div className='text-center py-4 bg-primary text-white'>
                    <div className='mb-3'>
                        {/* Simulación de imagen de perfil */}
                        <CiUser size={80} className='rounded-circle bg-white p-2' />
                    </div>
                    <p className='h5 mb-1'>{userData ? userData.name : 'Cargando...'}</p>
                    <p className='text-uppercase small'>Admin</p>
                </div>

                {/* Navigation panel */}
                <nav className='nav flex-column p-3'>
                    <Link to="ventas" className='nav-link text-dark py-2'>Ventas</Link>
                    <Link to="usuarios" className='nav-link text-dark py-2'>Usuarios</Link>
                    <Link to="reports" className='nav-link text-dark py-2'>Reports</Link>
                    <button className='btn btn-link nav-link text-dark py-2' onClick={handleLogout}>Cerrar Sesión</button>

                </nav>
            </aside>

            {/* Main content */}
            <main className='flex-grow-1 p-4'>
                <Outlet />
            </main>
        </div>
    );
};

export default AdminPanel;
