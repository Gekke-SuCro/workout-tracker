import {useState} from "react";
import {useNavigate, Link} from "react-router-dom";
import {useForm} from "react-hook-form";
import {toast} from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "../../assets/styles/formStyles.css";
import {useAuth} from "../../context/authContext";
import AuthInput from "../components/auth-forms/AuthFormInput.jsx";
import AuthFormLayout from "../components/auth-forms/AuthFormLayout.jsx";
import AuthSubmitButton from "../components/auth-forms/AuthFormSubmitButton.jsx";

const LoginPage = () => {
    const {login} = useAuth();
    const {register, handleSubmit, formState: {errors}} = useForm({mode: "onBlur"});
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const onSubmit = async (data) => {
        setLoading(true);
        const authToast = toast.loading("Logging in...");

        try {
            const username = await login(data.username, data.password);
            toast.update(authToast, {
                render: `Welcome back ${username}! 🎉`,
                type: "success",
                isLoading: false,
                autoClose: 3000
            });
            navigate("/");
        } catch (error) {
            toast.update(authToast, {render: error.message, type: "error", isLoading: false, autoClose: 3000});
        } finally {
            setLoading(false);
        }
    };

    return (
        <AuthFormLayout title="Login" onSubmit={handleSubmit(onSubmit)}>
            <p>Not a member? <Link to="/register" className="text-blue-500">Sign Up</Link></p>

            <div className="flex flex-col gap-4 py-8">
                <AuthInput label="Username" id="username" type="text" register={register}
                           validation={{required: "Username is required"}} error={errors.username}
                           placeholder="johndoe1"/>
                <AuthInput label="Password" id="password" type="password" register={register}
                           validation={{required: "Password is required"}} error={errors.password}
                           placeholder="********"/>
            </div>

            <AuthSubmitButton loading={loading} text="Login"/>
        </AuthFormLayout>
    );
};

export default LoginPage;
