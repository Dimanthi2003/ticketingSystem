from datetime import datetime

project_List = []
project_codes_set = set()  # Set to store unique project codes
no_of_workers = 100
ongoing = 0
onhold = 0
completed = 0
no_of_workers_added_later = 0

# Function to check if the input is a decimal number
def is_decimal(value):
    try:
        float(value)
        return True     
    except ValueError:
        return False

# Function to add a new project
def add_new_project():
    global no_of_workers, ongoing, completed, onhold, no_of_workers_added_later

    print("XYZ Company".center(50))
    print("Add a New Project".center(50))

    while True:
        project_code = input("Enter the Project Code (Enter '0' to exit): ")

        if project_code == '0':
            print("Exiting project addition.")
            return

        # Check if the project code already exists
        if project_code in project_codes_set:
            print("Error: Project code already exists. Please enter a unique project code.")
            continue
        else:
            project_codes_set.add(project_code)

        # Additional check for a valid project code (not a decimal number)
        if is_decimal(project_code):
            print("Error: Project code should not be a decimal number. Please enter a valid project code.")
            continue

        break

    while True:
        client_name = input("Enter the Client Name: ")
        if client_name.isalpha():
            break
        print("Please enter characters A-Z only")

    while True:
        start_date = input("Enter the start date (YYYY-MM-DD): ")

        try:
            start_date = datetime.strptime(start_date, "%Y-%m-%d").date()
            break
        except ValueError:
            print("Invalid date format. Please enter the date in the format YYYY-MM-DD.")

    while True:
        while True:
            end_date = input("Enter the end date (YYYY-MM-DD): ")

            try:
                end_date = datetime.strptime(end_date, "%Y-%m-%d").date()
                break
            except ValueError:
                print("Invalid date format. Please enter the date in the format YYYY-MM-DD.")

        if start_date <= end_date:
            break
        else:
            print("Error: End date must be on or after the start date. Please enter a valid end date.")

    while True:
        assign_workers = input("Enter the number of workers you need, Available (" + str(no_of_workers) + " workers): ")

        if assign_workers.isdigit() or (assign_workers[0] == '-' and assign_workers[1:].isdigit()):
            value = int(assign_workers)
            no_of_workers -= value
            print(f"{value} workers have been assigned.")
            break
        else:
            print("The input is not an integer. Please enter a valid number.")


    while True:
        project_status = input("Enter the current status (ongoing/completed/on hold): ").lower()


        if project_status in ["ongoing", "completed", "on hold"]:
            project_details = [project_code, client_name, start_date, end_date, no_of_workers, project_status]
            project_List.append(project_details)

            if project_status == "ongoing":
                ongoing += 1
            elif project_status == "completed":
                completed += 1
            elif project_status == "on hold":
                onhold += 1

            print("Project with these data added successfully!")
            break
        else:
            print("Invalid option. Please enter a valid project status (ongoing/completed/on hold).")
    project_statistics = input("Do you want to save the project (yes/no)? ").lower()
    if project_statistics == "yes":
        print("Project saved successfully ")
    else:
        print("Project not saved")


# Function to remove completed project


def remove_completed_project():
    global ongoing, completed, onhold

    print("XYZ Company".center(50))
    print("Remove Completed Project".center(50))

    project_code = input("Enter the Project code: ")
    remove_project = input(
        "Do you want to remove the project (yes/no): ").lower()

    if remove_project == "yes":
        found_project = None
        for project in project_List:
            if project[0] == project_code:
                found_project = project
                break

        if found_project:
            if found_project[5].lower() == "ongoing":
                ongoing -= 1
            elif found_project[5].lower() == "completed":
                completed -= 1
            elif found_project[5].lower() == "on hold":
                onhold -= 1

            project_List.remove(found_project)
            print(f"Project with code {project_code} deleted successfully!")
        else:
            print("Project removal successfully.")
    else:
        print("Project removal canceled.")

# Function to add new workers


def add_new_workers():
    global no_of_workers, no_of_workers_added_later

    print("XYZ Company".center(50))
    print("Add New Workers".center(50))

    to_add_workers = input("Do you want to add more workers? (Yes/no):")

    if to_add_workers.lower() == 'yes':
        no_of_workers_added_later = int(
            input("Enter the new number of workers: "))
        no_of_workers = (int(no_of_workers) +int(no_of_workers_added_later))
        print(f"{no_of_workers} are the new number of workers available!")
    else:
        print("No additional workers added.")


# Function to update project details


def update_project():
    global no_of_workers

    print("XYZ Company".center(50))
    print("Update Project Details".center(50))

    project_code_to_update = input(
        "Enter the project code to update (Enter '0' to exit): ")

    if project_code_to_update == '0':
        print("Exiting project update.")
        return

    for project in project_List: 
        if project[0] == project_code_to_update:
            print("Project code found")
            to_update = input(
                "Do you want to update the project (yes/no): ").lower()

            if to_update == 'yes':
                project[1] = input("Enter the new client name: ")
                project[2] = input("Enter the new start date (YYYY/MM/DD): ")
                project[3] = input("Enter the new end date (YYYY/MM/DD): ")

                no_of_workers_change = input(
                    "Do you want to change the number of workers you assign (yes/no): ")
                if no_of_workers_change.lower() == 'yes':
                    new_no_of_workers = int(
                        input("Enter the new number of workers you need: "))
                    if new_no_of_workers >= no_of_workers:
                        print(f"{new_no_of_workers} workers are available now.")
                    else:
                        print("The number of workers you want are not available.")

                project[5] = input(
                    "Enter the new project status (ongoing/completed/on hold): ")
                print(
                    f"Information updated for project {project_code_to_update}")
            else:
                print("No changes were made.")
            break
    else:
        print("Project code not found. Please enter a valid project code.")

    print("Updated list of projects:")
    for project in project_List:
        print(project)        

# Function to display project statistics


def project_statistics():
    global no_of_workers_added_later

    print("XYZ Company".center(50))
    print("Project Statistics".center(50))

    print("Number of ongoing projects:", ongoing)
    print("Number of completed projects:", completed)
    print("Number of on-hold projects:", onhold)
    no_of_assigned = (int(no_of_workers) + int(no_of_workers_added_later))
    print("Number of workers to assign:", no_of_assigned)

    project_statistics = input(
        "Do you want to add a new project (yes/no)? ").lower()
    if project_statistics == "yes":
        print("Project added successfully ")
    else:
        print("Project not added")

# Main program


def main():
    while True:
        print("XYZ Company".center(50))
        print("Main Menu".center(50))
        print("""Select Option:
        1. Add a new project to existing projects.
        2. Remove a completed project from existing projects.
        3. Add new workers to available workers group.
        4. Update details on ongoing projects.
        5. Project Statistics
        6. Exit""")
        choice = input("Enter your choice (1-6): ")

        if choice == '1':
            add_new_project()
        elif choice == '2':
            remove_completed_project()
        elif choice == '3':
            add_new_workers()
        elif choice == '4':
            update_project()
        elif choice == '5':
            project_statistics()
        elif choice == '6':
            print("Exiting the program.")
            break
        else:
            print("Invalid choice. Please enter a number between 1 and 6.")


if __name__ == "__main__":
    main()

