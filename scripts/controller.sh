#remove white spaces 
find . -depth -name '* *' \
| while IFS= read -r f ; do mv -i "$f" "$(dirname "$f")/$(basename "$f"|tr ' ' _)" ; done

#batch extract
for f in *.zip; do mkdir ${f}_dir; unzip $f -d ${f}_dir; done