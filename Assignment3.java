def metric_converter():
    print("Welcome to metric converter!")
    print("\nPlease input your query. For example, 1 km = m.")
    print("Enter 'exit' or '-1' to exit the program.")
    
    conversions = {
        "kg": ("lb", 2.20462),
        "g": ("oz", 0.035274),
        "km": ("mile", 0.621371),
        "mm": ("inch", 0.0393701)
    }
    
    while True:
        user_input = input("\nEnter conversion (e.g., '1 km = mile'): ").strip().lower()
        
        if user_input in ["exit", "-1"]:
            print("Goodbye!")
            break
        
        try:
            parts = user_input.split()
            if len(parts) != 4 or parts[2] != "=":
                raise ValueError
            
            value, from_unit, _, to_unit = parts
            value = float(value)
            
            if from_unit in conversions and conversions[from_unit][0] == to_unit:
                factor = conversions[from_unit][1]
                result = value * factor
                print(f"{value} {from_unit} = {result:.4f} {to_unit}")
            else:
                print("Your input is not currently handled by this app. Try something like '1 kg = lb'.")
        
        except ValueError:
            print("Invalid input format. Please enter a query like '1 km = mile'.")

if __name__ == "__main__":
    metric_converter()
