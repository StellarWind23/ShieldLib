import sys
import os

def main():
    if len(sys.argv) < 3:
        print("Usage: python file_search.py <directory> <search_string> [extension]")
        print("Example: python file_search.py ./ TODO .py")
        sys.exit(1)

    root_dir = sys.argv[1]
    needle = sys.argv[2].casefold()
    ext = sys.argv[3] if len(sys.argv) >= 4 else None

    for dirpath, _, filenames in os.walk(root_dir):
        for name in filenames:
            if ext and not name.endswith(ext):
                continue

            path = os.path.join(dirpath, name)
            try:
                with open(path, "r", encoding="utf-8", errors="ignore") as f:
                    for line in f:
                        if needle in line.casefold():
                            print(f"[FOUND] {path}")
                            break
            except Exception as e:
                print(f"[ERROR] {path}: {e}")

if __name__ == "__main__":
    main()
