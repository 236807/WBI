function [dotplot] = generatorDotPlot(sequence1,sequence2)
n=length(sequence1);
m=length(sequence2);

dotplot=zeros(length(n));
for i=1:n
    for j=1:m
        if (sequence1(i) == sequence2(j))
            dotplot(i,j)=1;
        end
    end
end
end
