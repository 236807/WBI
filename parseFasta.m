function [ structure ] = parseFasta( text )

SYMBOL_BEGIN = '>';
k = strfind(text,SYMBOL_BEGIN); % get nO of '>' chars
for i=1:length(k)
newStr = extractAfter (text,k(i)); 
intoLines = splitlines(newStr);
p = cellfun(@isempty,intoLines);
a = find(p);
if( isempty(a))
    a = length(intoLines);
end
temp = intoLines(2:a(1));
structure(i).header = intoLines(1);
structure(i).sequence = strjoin(temp);
end

end