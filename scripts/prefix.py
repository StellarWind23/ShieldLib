#!/usr/bin/env python3
import os
import sys
from glob import glob
import shutil

USAGE = (
    "Usage:\n"
    "  prefixer.py <directory> <glob> add <prefix> [copy]\n"
    "  prefixer.py <directory> <glob> remove <prefix> [copy]\n"
    "  prefixer.py <directory> <glob> replace <old_prefix> <new_prefix> [copy]\n"
    "\n"
    "copy = true | false (default: false)\n"
)

def exit_usage():
    print(USAGE)
    sys.exit(1)

def parse_copy():
    if len(sys.argv) > expected_args:
        return sys.argv[-1].lower() == "true"
    return False

if len(sys.argv) < 5:
    exit_usage()

directory = sys.argv[1]
pattern   = sys.argv[2]
mode      = sys.argv[3]

if not os.path.isdir(directory):
    print("Directory does not exist:", directory)
    sys.exit(1)

files = glob(os.path.join(directory, pattern))

# --- ADD ---
if mode == "add":
    expected_args = 5
    prefix = sys.argv[4]
    copy_mode = parse_copy()

    for f in files:
        dirname, filename = os.path.split(f)
        new_path = os.path.join(dirname, prefix + filename)

        if copy_mode:
            shutil.copy2(f, new_path)
        else:
            os.rename(f, new_path)

# --- REMOVE ---
elif mode == "remove":
    expected_args = 5
    prefix = sys.argv[4]
    copy_mode = parse_copy()

    for f in files:
        dirname, filename = os.path.split(f)
        if filename.startswith(prefix):
            new_path = os.path.join(dirname, filename[len(prefix):])

            if copy_mode:
                shutil.copy2(f, new_path)
            else:
                os.rename(f, new_path)

# --- REPLACE ---
elif mode == "replace":
    expected_args = 6
    if len(sys.argv) < expected_args:
        exit_usage()

    old_prefix = sys.argv[4]
    new_prefix = sys.argv[5]
    copy_mode = parse_copy()

    for f in files:
        dirname, filename = os.path.split(f)
        if filename.startswith(old_prefix):
            new_name = filename.replace(old_prefix, new_prefix, 1)
            new_path = os.path.join(dirname, new_name)

            if copy_mode:
                shutil.copy2(f, new_path)
            else:
                os.rename(f, new_path)

else:
    exit_usage()
