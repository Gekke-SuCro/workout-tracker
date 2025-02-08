import {Outlet} from "react-router-dom";
import {ToastContainer} from "react-toastify";

const MainLayout = () => {
    return (
        <div>
            {/* Global Toast Notifications */}
            <ToastContainer
                position="top-right"
                autoClose={3000}
                newestOnTop
                closeOnClick
                pauseOnHover
                limit={3}
            />

            {/* Render Page */}
            <Outlet />
        </div>
    )
}

export default MainLayout;