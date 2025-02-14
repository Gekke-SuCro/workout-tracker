import {set, useForm} from "react-hook-form";
import CreateWorkoutFormLayout from "../components/forms/CreateWorkoutFormLayout.jsx";
import ReactHookInput from "../components/forms/input/ReactHookInput.jsx";
import AuthSubmitButton from "../components/forms/AuthFormSubmitButton.jsx";
import {useState} from "react";

const LogWorkoutPage = () => {
    const {
        register,
        handleSubmit,
        formState: {errors}
    } = useForm({mode: "onBlur"});
    const [loading, setLoading] = useState(false);

    const onSubmit = async (data) => {
        setLoading(true);
        console.log(data);
        setLoading(false);
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
