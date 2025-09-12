import logging

# Placeholder imports for the helper functions assumed to exist elsewhere in the project.
# These functions should provide the following behaviors:
# - load_questions(path) -> Iterable of questions
# - build_gemini_prompt(question) -> str
# - generate_variation_with_gemini(prompt) -> variation object
# - format_for_csv(question, variation) -> mapping/row ready for CSV
# - save_variations_to_csv(path, row) -> None (appends row to CSV)
try:
    from utils import (
        load_questions,
        build_gemini_prompt,
        generate_variation_with_gemini,
        format_for_csv,
        save_variations_to_csv,
    )
except Exception:  # pragma: no cover - fallback for missing modules during static analysis
    pass


def run_full_pipeline(input_file: str, output_file: str, variations_per_question: int = 10) -> None:
    """Generate variations for all questions and save them to a CSV file.

    Args:
        input_file: Path to the CSV containing original questions.
        output_file: Path to the CSV where generated variations will be saved.
        variations_per_question: Number of variations to generate per question.

    This function performs the following steps:
        1. Load questions from ``input_file`` using :func:`load_questions`.
        2. For each question, generate ``variations_per_question`` variations by:
            a. Building a Gemini prompt using :func:`build_gemini_prompt`.
            b. Generating one variation with :func:`generate_variation_with_gemini`.
            c. Formatting the variation for CSV via :func:`format_for_csv`.
            d. Saving the formatted variation with :func:`save_variations_to_csv`.
           Any exceptions raised during variation generation are logged and the loop
           continues with the next variation.
        3. Logs summary information including counts of processed questions,
           generated variations, and failures.

    Example:
        >>> run_full_pipeline(
        ...     "data/input_questions.csv",
        ...     "data/generated_variations.csv",
        ...     variations_per_question=10,
        ... )
    """

    questions = list(load_questions(input_file))
    total_questions = len(questions)
    logging.info("Loaded %d questions", total_questions)

    generated_count = 0
    failure_count = 0

    for index, question in enumerate(questions, start=1):
        logging.info("Processing question %d/%d", index, total_questions)
        for _ in range(variations_per_question):
            try:
                prompt = build_gemini_prompt(question)
                variation = generate_variation_with_gemini(prompt)
                formatted = format_for_csv(question, variation)
                save_variations_to_csv(output_file, formatted)
                generated_count += 1
            except Exception:  # noqa: BLE001 - broad for logging and continuation
                logging.exception(
                    "Failed to generate variation for question %d", index
                )
                failure_count += 1
                continue

    logging.info(
        "Processing complete. Questions processed: %d, Variations generated: %d, Failures: %d",
        total_questions,
        generated_count,
        failure_count,
    )

