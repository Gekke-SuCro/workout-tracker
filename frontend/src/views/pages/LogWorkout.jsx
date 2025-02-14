import {set, useForm} from "react-hook-form";
import CreateWorkoutFormLayout from "../components/forms/CreateWorkoutFormLayout.jsx";
import ReactHookInput from "../components/forms/input/ReactHookInput.jsx";
import AuthSubmitButton from "../components/forms/AuthFormSubmitButton.jsx";
import {useState} from "react";
import {WorkoutAPI} from "../../api/WorkoutAPI.js";
import {toast} from "react-toastify";
import {useNavigate} from "react-router-dom";

const LogWorkoutPage = () => {
    const {
        register,
        handleSubmit,
        formState: {errors}
    } = useForm({mode: "onBlur"});

    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();


    const onSubmit = async (data) => {
        setLoading(true);
        const createWorkoutToast = toast.loading("Logging workout...");

        try {
            await WorkoutAPI.create(data.workoutName, data.workoutDate, []);
            toast.update(createWorkoutToast, {
                render: `'${data.workoutName}' workout logged`,
                type: "success",
                isLoading: false,
                autoClose: 3000
            });
            navigate("/");
        } catch (error) {
            toast.update(createWorkoutToast, {render: error.message, type: "error", isLoading: false, autoClose: 3000});
        } finally {
            setLoading(false);
        }
    }

    return (
        <div className="">
            <CreateWorkoutFormLayout onSubmit={handleSubmit(onSubmit)} title="Log Workout">
                <div className="flex flex-col gap-4 py-8">
                    <ReactHookInput label="Workout Name" id="workoutName" type="text" register={register}
                                    validation={{required: "Workout name is required"}} error={errors.workoutName}
                                    placeholder="Leg Day Workout"/>

                    <ReactHookInput label="Workout Date" id="workoutDate" type="date" register={register}
                                    validation={{required: "Workout date is required"}} error={errors.workoutDate}
                                    placeholder="Leg Day Workout"
                                    defaultValue={new Date().toISOString().split("T")[0]}/>
                </div>

                <AuthSubmitButton loading={loading} text="Log Workout"/>
            </CreateWorkoutFormLayout>
        </div>
    );
};

export default LogWorkoutPage;
