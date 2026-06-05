#!/usr/bin/env python3
import os
import sys

def replace_in_file(filepath, target, replacement):
    """Replace target string with replacement inside a text file."""
    try:
        with open(filepath, "r", encoding="utf-8") as f:
            content = f.read()
    except UnicodeDecodeError:
        # Skip binary / unreadable files
        return

    if target in content:
        new_content = content.replace(target, replacement)
        with open(filepath, "w", encoding="utf-8") as f:
            f.write(new_content)
        print(f"[UPDATED CONTENT] {filepath}")

def process_directory(root_dir, target, replacement, ext):
    for dirpath, dirnames, filenames in os.walk(root_dir):
        for filename in filenames:
            old_path = os.path.join(dirpath, filename)
            new_filename = filename.replace(target, replacement)
            new_path = os.path.join(dirpath, new_filename)

            # Replace inside file contents only if extension matches
            if filename.endswith(ext):
                replace_in_file(old_path, target, replacement)

            # Rename file if needed
            if new_filename != filename:
                os.rename(old_path, new_path)
                print(f"[RENAMED FILE] {old_path} -> {new_path}")
                old_path = new_path  # update path after rename

        # Rename directories (bottom-up so paths stay valid)
        for dirname in list(dirnames):
            old_dir = os.path.join(dirpath, dirname)
            new_dirname = dirname.replace(target, replacement)
            new_dir = os.path.join(dirpath, new_dirname)

            if new_dirname != dirname:
                os.rename(old_dir, new_dir)
                print(f"[RENAMED DIR] {old_dir} -> {new_dir}")

def main():
    if len(sys.argv) != 5:
        print("Usage: python replace_recursive.py <root_dir> <target_string> <replacement_string> <file_extension>")
        print("Example: python replace_recursive.py ./ myOldName myNewName .txt")
        sys.exit(1)

    root_dir = sys.argv[1]
    target = sys.argv[2]
    replacement = sys.argv[3]
    ext = sys.argv[4]

    if replacement == "!":
        replacement = ""

    #Shortcuts
    target = target.replace("%%"," ")
    replacement = replacement.replace("%%"," ")

    target = target.replace("%r","\r")
    replacement = replacement.replace("%r","\r")

    target = target.replace("%n","\n")
    replacement = replacement.replace("%n","\n")

    print("Target: " + target)

    if not os.path.isdir(root_dir):
        print(f"Error: {root_dir} is not a valid directory")
        sys.exit(1)

    process_directory(root_dir, target, replacement, ext)

if __name__ == "__main__":
    main()
