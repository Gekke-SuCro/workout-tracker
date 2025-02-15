import PropTypes from "prop-types";

const CreateWorkoutFormLayout = ({title, children, onSubmit}) => {
    return (
        <div className="mx-auto sm:max-w-md mt-24">
            <form
                onSubmit={onSubmit}
                className="p-8"
            >
                <h1 className="title mb-4">{title}</h1>
                {children}
            </form>
        </div>
    );
};

CreateWorkoutFormLayout.propTypes = {
    title: PropTypes.string.isRequired,
    children: PropTypes.node.isRequired,
    onSubmit: PropTypes.func.isRequired,
}

export default CreateWorkoutFormLayout;
