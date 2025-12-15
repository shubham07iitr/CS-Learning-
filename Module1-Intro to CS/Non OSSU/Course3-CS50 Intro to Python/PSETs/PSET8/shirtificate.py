from fpdf import FPDF

def main():
    name = input("Name: ")
    generate_shirtificate(name)

def generate_shirtificate(name):
    pdf = FPDF(orientation="P", unit="mm", format="A4")
    pdf.add_page()

    # Set title font
    pdf.set_font("Helvetica", "B", 24)
    pdf.set_text_color(0, 0, 0)
    pdf.cell(0, 30, "CS50 Shirtificate", align="C", ln=True)

    # Add shirt image
    pdf.image("shirtificate.png", x=25, y=60, w=160)

    # Add name in white text over shirt
    pdf.set_font("Helvetica", "B", 20)
    pdf.set_text_color(255, 255, 255)
    pdf.set_y(145)  # approximate y-coordinate where text should appear over shirt
    pdf.cell(0, 10, f"{name} took CS50", align="C")

    # Save the PDF
    pdf.output("shirtificate.pdf")

if __name__ == "__main__":
    main()
