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
import {
    PASSWORD_MAX_LENGTH, PASSWORD_MIN_LENGTH, PASSWORD_REGEX, PASSWORD_REGEX_MESSAGE,
    USERNAME_MAX_LENGTH,
    USERNAME_MIN_LENGTH,
    USERNAME_REGEX,
    USERNAME_REGEX_MESSAGE
} from "../../constants/authValidationConstants.js";

const RegisterPage = () => {
    const {registerNewUser} = useAuth();
    const {register, handleSubmit, formState: {errors}, watch} = useForm({mode: "onBlur"});
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const onSubmit = async (data) => {
        setLoading(true);
        const authToast = toast.loading("Creating account...");

        try {
            await registerNewUser(data.username, data.password, data.confirmPassword);
            toast.update(authToast, {
                render: "Account created, you can now login! 🎉",
                type: "success",
                isLoading: false,
                autoClose: 3000
            });
            navigate("/login");
        } catch (error) {
            toast.update(authToast, {render: error.message, type: "error", isLoading: false, autoClose: 3000});
        } finally {
            setLoading(false);
        }
    };

    return (
        <AuthFormLayout title="Sign Up" onSubmit={handleSubmit(onSubmit)}>
            <p>Already have an account? <Link to="/login" className="text-blue-500">Login</Link></p>

            <div className="flex flex-col gap-4 py-8">
                <AuthInput label="Username*" id="username" type="text" register={register} validation={{
                    required: "Username is required",
                    minLength: {value: USERNAME_MIN_LENGTH, message: `Must be at least ${USERNAME_MIN_LENGTH} characters.`},
                    maxLength: {value: USERNAME_MAX_LENGTH, message: `Cannot exceed ${USERNAME_MAX_LENGTH} characters.`},
                    pattern: {value: new RegExp(USERNAME_REGEX), message: USERNAME_REGEX_MESSAGE}
                }} error={errors.username} placeholder="johndoe1"/>

                <AuthInput label="Password*" id="password" type="password" register={register} validation={{
                    required: "Password is required",
                    minLength: {value: PASSWORD_MIN_LENGTH, message: `At least ${PASSWORD_MIN_LENGTH} characters.`},
                    maxLength: {value: PASSWORD_MAX_LENGTH, message: `Cannot exceed ${PASSWORD_MAX_LENGTH} characters.`},
                    pattern: {value: new RegExp(PASSWORD_REGEX), message: PASSWORD_REGEX_MESSAGE}
                }} error={errors.password} placeholder="********"/>

                <AuthInput label="Confirm Password*" id="confirmPassword" type="password" register={register}
                           validation={{
                               required: "Confirm password is required",
                               validate: (value) => value === watch("password") || "Passwords do not match."
                           }} error={errors.confirmPassword} placeholder="********"/>
            </div>

            <AuthSubmitButton loading={loading} text="Sign Up"/>
        </AuthFormLayout>
    );
};

export default RegisterPage;
